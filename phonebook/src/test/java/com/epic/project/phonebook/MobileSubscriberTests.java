package com.epic.project.phonebook;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MobileSubscriberTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  MobileSubscriberRepository repository;

  @Test
  public void shouldFindNoMobilesIfRepositoryIsEmpty() {
	  
	  Iterable<MobileSubscriber> mobiles = repository.findAll();
      assertThat(mobiles).isEmpty();
  }

  @Test
  public void shouldStoreMobile() {
	
      MobileSubscriber mobile = new MobileSubscriber( "9196452367", Long.valueOf(2), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));
      repository.save(mobile);
      assertThat(mobile).hasFieldOrPropertyWithValue("msisdn", "9196452367");
  }

  @Test
  public void shouldFindAllMobiles() {
	  
	  MobileSubscriber mobile1 = new MobileSubscriber("9196452167", Long.valueOf(4), Long.valueOf(5), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));
      entityManager.persist(mobile1);

      MobileSubscriber mobile2 = new MobileSubscriber("91963459967", Long.valueOf(4), Long.valueOf(5), Type.MOBILE_PREPAID, Long.valueOf("16377733342345"));    
      entityManager.persist(mobile2);

      MobileSubscriber mobile3 = new MobileSubscriber("9877523554", Long.valueOf(4), Long.valueOf(5), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));    
      entityManager.persist(mobile3);

      Iterable<MobileSubscriber> mobiles = repository.findAll();
      assertThat(mobiles).hasSize(3).contains(mobile1, mobile2, mobile3);
  }

  @Test
  public void shouldFindMobileById() {
	  MobileSubscriber mobile1 = new MobileSubscriber( "9196452367", Long.valueOf(2), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));		
      entityManager.persist(mobile1);

      MobileSubscriber mobile2 = new MobileSubscriber( "9196345367", Long.valueOf(2), Long.valueOf(3), Type.MOBILE_PREPAID, Long.valueOf("16377733342345"));    
      entityManager.persist(mobile2);

      MobileSubscriber foundMobile = repository.findById(mobile2.getId()).get();
      assertThat(foundMobile).isEqualTo(mobile2);
  }

  @Test
  public void shouldFindMobileByOwnerid() {
	  MobileSubscriber mobile1 = new MobileSubscriber("9196452367", Long.valueOf(7), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile1);

	  MobileSubscriber mobile2 = new MobileSubscriber("9196345367", Long.valueOf(8), Long.valueOf(5), Type.MOBILE_PREPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile2);

	  MobileSubscriber mobile3 = new MobileSubscriber( "9877523554", Long.valueOf(4), Long.valueOf(6), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile3);

      Iterable<MobileSubscriber> mobiles = repository.findByOwnerId(mobile2.getOwner());
      assertThat(mobiles).hasSize(1).contains(mobile2);
  }

  @Test
  public void shouldFindMobileByMsisdn() {
	  MobileSubscriber mobile1 = new MobileSubscriber("9196452367", Long.valueOf(7), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile1);

      MobileSubscriber foundMobile = repository.findByMsisdn("9196452367");
      assertThat(foundMobile).isEqualTo(mobile1);
  }

  @Test
  public void shouldUpdateMobileById() {
	  MobileSubscriber mobile1 = new MobileSubscriber("9196452367", Long.valueOf(7), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile1);

	  MobileSubscriber mobile2 = new MobileSubscriber("9196345367", Long.valueOf(8), Long.valueOf(5), Type.MOBILE_PREPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile2);

      MobileSubscriber updatedMobile = new MobileSubscriber( "9877523554", Long.valueOf(4), Long.valueOf(6), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));
    
      MobileSubscriber mob = repository.findById(mobile2.getId()).get();
      mob.setMsisdn(updatedMobile.getMsisdn());
      mob.setType(updatedMobile.getType());
      mob.setUser(updatedMobile.getUser());
      repository.save(mob);

      MobileSubscriber checkMobile = repository.findById(mobile2.getId()).get();
    
      assertThat(checkMobile.getId()).isEqualTo(mobile2.getId());
      assertThat(checkMobile.getMsisdn()).isEqualTo(updatedMobile.getMsisdn());
      assertThat(checkMobile.getType()).isEqualTo(updatedMobile.getType());
      assertThat(checkMobile.getUser()).isEqualTo(updatedMobile.getUser());
  }

  @Test
  public void shouldDeleteMobileById() {
	  MobileSubscriber mobile1 = new MobileSubscriber("9196452367", Long.valueOf(7), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile1);

	  MobileSubscriber mobile2 = new MobileSubscriber("9196345367", Long.valueOf(8), Long.valueOf(5), Type.MOBILE_PREPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile2);

	  MobileSubscriber mobile3 = new MobileSubscriber("9877523554", Long.valueOf(4), Long.valueOf(6), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile3);

      repository.deleteById(mobile2.getId());

      Iterable<MobileSubscriber> mobiles = repository.findAll();
      assertThat(mobiles).hasSize(2).contains(mobile1,mobile3);
  }

  @Test
  public void shouldDeleteAllMobiles() {
	  MobileSubscriber mobile1 = new MobileSubscriber( "9196452367", Long.valueOf(7), Long.valueOf(3), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile1);

	  MobileSubscriber mobile2 = new MobileSubscriber( "9196345367", Long.valueOf(8), Long.valueOf(5), Type.MOBILE_PREPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile2);

	  MobileSubscriber mobile3 = new MobileSubscriber("9877523554", Long.valueOf(4), Long.valueOf(6), Type.MOBILE_POSTPAID, Long.valueOf("16377733342345"));	    
	  entityManager.persist(mobile3);

      repository.deleteAll();
      assertThat(repository.findAll()).isEmpty();
  }
}