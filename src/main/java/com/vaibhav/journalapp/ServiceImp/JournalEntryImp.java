package com.vaibhav.journalapp.ServiceImp;


import com.vaibhav.journalapp.Repository.JournalEntryRepository;
import com.vaibhav.journalapp.Repository.UserRepository;
import com.vaibhav.journalapp.Service.JournalEntryService;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.JournalEntry;
import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryImp implements JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public boolean createEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUsername(userName);

            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(saved);

            userService.saveExistUser(user);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating journal entry", e);

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
    @Transactional
    public boolean deleteJournalEntryById(ObjectId myId, String userName) {
        try {

            if (journalEntryRepository.existsById(myId)) {

                User user = userService.findByUsername(userName);
              boolean removed=  user.getJournalEntries().removeIf(x -> x.getId().equals(myId));

                if(removed){

                    userService.saveExistUser(user);
                    journalEntryRepository.deleteById(myId);
                    return true;
                }else {

                    return false;
                }


            } else {

                return false;
            }

        } catch (Exception e) {
           throw new RuntimeException("An error occurred while deleting journal entry", e);
        }
    }

    @Override
    public boolean updateJournalById(ObjectId myId, JournalEntry newEntry, String userName) {
        User user = userService.findByUsername(userName);
//        if(user!=null){
//           Future Work

//        }
        if (journalEntryRepository.existsById(myId)) {
            newEntry.setId(myId);
            journalEntryRepository.save(newEntry);
            return true;
        } else {
            return false;
        }
    }

}
