package org.example.processmanager.pojo;
/**
 * @author cyl
 */
public class Service {
    public String name;
    public String status;
    public Service() {
    }
    public Service(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
