package com.epic.project.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobileSubscriberService {
    private final MobileSubscriberRepository mobileSubscriberRespository;

    @Autowired
    public MobileSubscriberService(MobileSubscriberRepository mobileSubscriberRespository) {
        this.mobileSubscriberRespository = mobileSubscriberRespository;
    }

    public List<MobileSubscriber> findAll() {
        return (List<MobileSubscriber>) mobileSubscriberRespository.findAll();
    }

    public Optional<MobileSubscriber> findById(Long id) {
        return mobileSubscriberRespository.findById(id);
    }

    public MobileSubscriber save(MobileSubscriber subscriber) {
        return mobileSubscriberRespository.save(subscriber);
    }

    public void deleteById(Long id) {
    	mobileSubscriberRespository.deleteById(id);
    }
    
    public MobileSubscriber getSubscribersByMsisdn(String msisdn) {
        return mobileSubscriberRespository.findByMsisdn(msisdn);
    }
    
    public List<MobileSubscriber> getSubscribersByType(Type servicetype) {
        return mobileSubscriberRespository.findByServiceType(servicetype);
    }
    
    public List<MobileSubscriber> getSubscribersByOwner(Long ownerId) {
        return mobileSubscriberRespository.findByOwnerId(ownerId);
    }
    
    public List<MobileSubscriber> getSubscribersByUser(Long userId) {
        return mobileSubscriberRespository.findByUserId(userId);
    }
    
    public List<MobileSubscriber> getSubscribersByOwnerIdAndUserId(Long ownerId, Long userId) {
        return mobileSubscriberRespository.findByOwnerIdAndUserId(ownerId,userId);
    }
    
    public List<MobileSubscriber> getSubscribersByUserIdAndType(Long userId, Type servicetype) {
        return mobileSubscriberRespository.findByUserIdAndServiceType(userId,servicetype);
    }
}