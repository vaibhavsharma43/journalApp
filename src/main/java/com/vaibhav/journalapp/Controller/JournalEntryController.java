package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Service.JournalEntryService;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.JournalEntry;
import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {
      User user=  userService.findByUsername(userName);
        List<JournalEntry> entries = user.getJournalEntries();
        return ResponseEntity.ok(entries);
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName ) {

       try{
        boolean created=   journalEntryService.createEntry(myEntry,userName);
        if(created){
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        return ResponseEntity.ok(journalEntryService.getJournalEntryById(myId));

    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId ,@PathVariable String userName) {
        boolean report = journalEntryService.deleteJournalEntryById(myId,userName);
        if (report) {

            return ResponseEntity.ok("Entry gets deleted");
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,
                                               @RequestBody JournalEntry newEntry,
                                               @PathVariable String userName) {
        boolean report = journalEntryService.updateJournalById(myId, newEntry,userName);

        if (report) {

            return ResponseEntity.ok("Entry gets updated");
        } else {

            return ResponseEntity.notFound().build();
        }

    }
}


