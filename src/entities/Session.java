package entities;

public class Session {
    private static User userCon;

    public static User getUserCon() {
        return userCon;
    }

    public static void setUserCon(User userCon) {
        Session.userCon = userCon;
    }
}
