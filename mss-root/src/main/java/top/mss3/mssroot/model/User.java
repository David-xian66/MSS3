package top.mss3.mssroot.model;

public class User {
    private final String UserName;
    private final String Password;

    public User(String UserName, String Password){
        this.UserName = UserName;
        this.Password = Password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User other){
            return other.getUserName().equals(this.UserName)
                   &&
                   other.getPassword().equals(this.Password);
        }
        return false;
    }
}
