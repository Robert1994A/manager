package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.inf.ucv.admitere.entity.File;

public interface FileRepository extends JpaRepository<File, String> {

}
