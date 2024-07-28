package com.vaibhav.journalapp.Controller;

import com.vaibhav.journalapp.Service.JournalEntryService;
import com.vaibhav.journalapp.Service.UserService;
import com.vaibhav.journalapp.entity.JournalEntry;
import com.vaibhav.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> allEntries = user.getJournalEntries();
        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {


        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            boolean created = journalEntryService.createEntry(myEntry, userName);
            if (created) {
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User  user = userService.findByUsername(userName);
 List<JournalEntry>collect=user.getJournalEntries().stream().filter(x ->x.getId().equals(myId)).toList();
 if(!collect.isEmpty()){
     return ResponseEntity.ok(journalEntryService.getJournalEntryById(myId));
 }else{
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }


    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean report = journalEntryService.deleteJournalEntryById(myId, userName);
        if (report) {

            return ResponseEntity.ok("Entry gets deleted");
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,
                                               @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User  user = userService.findByUsername(userName);
        List<JournalEntry>collect=user.getJournalEntries().stream().filter(x ->x.getId().equals(myId)).toList();
        if(!collect.isEmpty()){
            boolean report = journalEntryService.updateJournalById(myId, newEntry, userName);
            if (report) {

                return ResponseEntity.ok("Entry gets updated");
            }
            else {

                return ResponseEntity.notFound().build();
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}


