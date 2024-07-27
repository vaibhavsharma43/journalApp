package com.vaibhav.journalapp.Service;


import com.vaibhav.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JournalEntryService {
    boolean createEntry(JournalEntry journalEntry, String user);




    List<JournalEntry> getAllJournalEntriesOfUser();

    JournalEntry getJournalEntryById(ObjectId myId);

    boolean deleteJournalEntryById(ObjectId myId,String user);

    boolean updateJournalById(ObjectId myId, JournalEntry newEntry, String userName);
}
