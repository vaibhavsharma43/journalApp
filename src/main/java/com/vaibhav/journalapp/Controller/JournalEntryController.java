package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Service.JournalEntryService;
import com.vaibhav.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/entries")
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        List<JournalEntry> entries = journalEntryService.getAllJournalEntriesOfUser();
        return ResponseEntity.ok(entries);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
     boolean report=   journalEntryService.createEntry(myEntry);
     if(report){
         return ResponseEntity.ok("Entry done");
     }else{
         return ResponseEntity.ok("Entry failed");
     }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
      return ResponseEntity.ok(  journalEntryService.getJournalEntryById(myId));

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        boolean report= journalEntryService.deleteJournalEntryById(myId);
        if(report){

            return ResponseEntity.ok("Entry gets deleted");
        }else{

                return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        boolean report=  journalEntryService.updateJournalById(myId,newEntry);
        if(report){
            return ResponseEntity.ok("Entry gets updated");
        }else{

          return   ResponseEntity.notFound().build();
        }

    }
}


