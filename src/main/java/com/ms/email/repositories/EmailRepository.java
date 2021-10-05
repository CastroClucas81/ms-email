package com.ms.email.repositories;

import com.ms.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

//vamos extender o jpaRepository para ela extender os métodos findAll, findById, etc...
//tenho q passar a entity para o jpa e o id q é do tipo long
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
