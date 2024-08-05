package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.VoteDTO;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.api.IVoteStorage;


public class VoteService implements IVoteService {

    private final static VoteService instance = new VoteService();
    private final static IVoteStorage voteStorage = VoteStorageFile.getInstance();

    private VoteService() {
    }

    @Override
    public void create(VoteDTO vote) {
        if(vote.getArtist() == null || vote.getArtist().isBlank()){
            throw new IllegalArgumentException("Артист пуст");
        }
        voteStorage.create(vote);
    }

    public static VoteService getInstance(){
        return instance;
    }
}
