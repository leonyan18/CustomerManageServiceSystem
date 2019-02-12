package entity;

/**
 * @author yan
 */

public enum UserType {
    /**
     * description:
     * @return
     */
    ADMIN(0),
    STAFF(1),
    USER(2),
    ROBOT(3);
    private int code;
    UserType(int code) {
        this.code=code;
    }

}
