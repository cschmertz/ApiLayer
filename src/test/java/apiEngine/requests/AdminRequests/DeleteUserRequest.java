package apiEngine.requests.AdminRequests;

public class DeleteUserRequest {
    private int id;

    public DeleteUserRequest() {}

    public DeleteUserRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
