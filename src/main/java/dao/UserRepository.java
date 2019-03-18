package dao;

import dto.UserDTO;
import entity.UserEntity;
import entity.UserType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yan
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * description: 通过id查找用户
     *
     * @param uid
     * @return entity.UserEntity
     */
    UserEntity findByUid(int uid);

    /**
     * description:
     *
     * @param
     * @return java.util.List<dto.UserDTO>
     */
    @Query("select new dto.UserDTO(u)from UserEntity u where u.type=1")
    List<UserDTO> findAllStaff();

    @Query("select new dto.UserDTO(u)from UserEntity u where u.name like ?1 or u.username like ?1")
    List<UserDTO> findAllUserByKeyword(String keyword, Pageable pageable);

    @Query("select new dto.UserDTO(u)from UserEntity u where u.type=?1 and ( u.name like ?2 or u.username like ?2)")
    List<UserDTO> findAllUserByKeywordAndType(UserType type, String keyword, Pageable pageable);

    @Query("select count (u)from UserEntity u where u.name like ?1 or u.username like ?1")
    long countAllUserByKeyword(String keyword);

    @Query("select count (u)from UserEntity u where u.type=?1 and ( u.name like ?2 or u.username like ?2)")
    long countAllUserByKeywordAndType(UserType type, String keyword);

}
