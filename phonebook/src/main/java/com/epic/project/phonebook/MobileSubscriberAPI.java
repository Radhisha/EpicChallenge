package com.epic.project.phonebook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@Validated
@RequestMapping("/api/v1/mobiles")
public class MobileSubscriberAPI{
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final MobileSubscriberService mobSubscriberService;

    @Autowired
    public MobileSubscriberAPI(MobileSubscriberService mobSubscriberService) {
        this.mobSubscriberService = mobSubscriberService;
    }

    @GetMapping
    public ResponseEntity<List<MobileSubscriber>> findAll() {
    	LOGGER.info("About to display mobile collection, if any..");
        return ResponseEntity.ok(mobSubscriberService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobileSubscriber> findById(@PathVariable Long id) {
        LOGGER.info("Details for Mobile :");
        return ResponseEntity.ok(mobSubscriberService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<MobileSubscriber> create(@Validated @RequestBody MobileSubscriber mobSubscrbr ) {
    	LOGGER.info("Creating Mobile record :");
        return ResponseEntity.status(HttpStatus.CREATED).body(mobSubscriberService.save(mobSubscrbr));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MobileSubscriber> update (@PathVariable Long id, @RequestBody MobileSubscriber mobileSubscriber) throws ResourceNotFoundException {
    	LOGGER.info("About to update mobile record :");
    	MobileSubscriber subscriber = mobSubscriberService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscriber not found for this id :: " + id));
        subscriber.setMsisdn(mobileSubscriber.getMsisdn());
    	subscriber.setOwner(mobileSubscriber.getOwner());
        subscriber.setUser(mobileSubscriber.getUser());
    	subscriber.setType(mobileSubscriber.getType());
        return ResponseEntity.accepted().body(mobSubscriberService.save(subscriber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MobileSubscriber> delete(@PathVariable Long id) {
    	LOGGER.info("Deleting Mobile record :");
    	mobSubscriberService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/msisdn/{msisdn}")
    public ResponseEntity<MobileSubscriber> getSubscribersByMsisdn(@PathVariable String msisdn ) {
    	LOGGER.info("Searching Mobile record using msisdn :");
        return ResponseEntity.ok(mobSubscriberService.getSubscribersByMsisdn(msisdn));
    }
    
    @GetMapping("/servicetype/{servicetype}")
    public ResponseEntity<List<MobileSubscriber>> getSubscribersByType(@PathVariable Type servicetype ) {
    	LOGGER.info("Searching Mobile record using serviceType :");
        return ResponseEntity.ok(mobSubscriberService.getSubscribersByType(servicetype));
    }    
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<MobileSubscriber>> getSubscribersByOwner(@PathVariable Long ownerId ) {
    	LOGGER.info("Searching Mobile record using owner :");
        return ResponseEntity.ok(mobSubscriberService.getSubscribersByOwner(ownerId));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MobileSubscriber>> getSubscribersByUser(@PathVariable Long userId) {
    	LOGGER.info("Searching Mobile record using user :");
        return ResponseEntity.ok(mobSubscriberService.getSubscribersByUser(userId));
    }
    
    @GetMapping("/owneridanduserid")
    public ResponseEntity<List<MobileSubscriber>> getSubscribersByOwnerIdAndUserId(@RequestParam Long ownerid,@RequestParam Long userid ) {
    	LOGGER.info("Searching Mobile record using owner and user :");
        return ResponseEntity.ok(mobSubscriberService.getSubscribersByOwnerIdAndUserId(ownerid,userid));
    }
    
    @GetMapping("/useridandservicetype")
    public ResponseEntity<List<MobileSubscriber>> getSubscribersByUserIdAndType(@RequestParam Long userid, @RequestParam Type servicetype ) {
    	LOGGER.info("Searching Mobile record using user and serviceType :");
        return ResponseEntity.ok(mobSubscriberService.getSubscribersByUserIdAndType(userid,servicetype));
    }
}