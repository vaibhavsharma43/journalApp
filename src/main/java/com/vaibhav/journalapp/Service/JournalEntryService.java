package com.vaibhav.journalapp.Service;


import com.vaibhav.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface JournalEntryService  {
    boolean createEntry(JournalEntry journalEntry);
    List<JournalEntry> getAllJournalEntriesOfUser();
    JournalEntry getJournalEntryById( ObjectId myId);
    boolean deleteJournalEntryById( ObjectId myId);
    boolean updateJournalById( ObjectId myId,  JournalEntry newEntry);
}
