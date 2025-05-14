package com.spring.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ims.entity.Invoice;
import com.spring.ims.entity.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

	List<Invoice> findAllByUserAndType(User user, String type);

}
