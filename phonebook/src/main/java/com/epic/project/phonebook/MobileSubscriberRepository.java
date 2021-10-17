package com.epic.project.phonebook;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MobileSubscriberRepository extends PagingAndSortingRepository<MobileSubscriber, Long> {
	
	MobileSubscriber findByMsisdn(String msisdn);
	List<MobileSubscriber> findByServiceType(Type serviceType);
	List<MobileSubscriber> findByOwnerId(Long ownerId);
	List<MobileSubscriber> findByUserId(Long userId);
	List<MobileSubscriber> findByOwnerIdAndUserId(Long ownerId, Long userId);
	List<MobileSubscriber> findByUserIdAndServiceType(Long userId, Type serviceType);
}