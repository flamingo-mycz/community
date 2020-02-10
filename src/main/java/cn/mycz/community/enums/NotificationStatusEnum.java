package cn.mycz.community.enums;

/**
 * @author 木已成舟
 * @date 2020/2/10
 */
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);

    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
