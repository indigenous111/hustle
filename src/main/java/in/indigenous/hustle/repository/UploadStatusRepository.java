package in.indigenous.hustle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.indigenous.hustle.entities.UploadStatus;

@Repository
public interface UploadStatusRepository extends JpaRepository<UploadStatus, Long>{

}
