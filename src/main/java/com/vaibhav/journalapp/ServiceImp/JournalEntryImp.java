package com.vaibhav.journalapp.ServiceImp;


import com.vaibhav.journalapp.Repository.JournalEntryRepository;
import com.vaibhav.journalapp.Service.JournalEntryService;
import com.vaibhav.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JournalEntryImp  implements JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Override
    public boolean createEntry(JournalEntry journalEntry) {
        try{
            journalEntryRepository.save(journalEntry);
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public List<JournalEntry> getAllJournalEntriesOfUser() {

        return journalEntryRepository.findAll();
    }

    @Override
    public JournalEntry getJournalEntryById(ObjectId myId) {
        return journalEntryRepository.findById(myId).orElse(null);
    }

    @Override
    public boolean deleteJournalEntryById(ObjectId myId) {
        try{
            if(journalEntryRepository.existsById(myId)){
                journalEntryRepository.deleteById(myId);
                return  true;
            }else{
                return false;
            }

        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public boolean updateJournalById(ObjectId myId, JournalEntry newEntry) {
        if(journalEntryRepository.existsById(myId)){
            newEntry.setId(myId);
            journalEntryRepository.save(newEntry);
        return  true;
        }else{
            return false;
        }
    }
}
