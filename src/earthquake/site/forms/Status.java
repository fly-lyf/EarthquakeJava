package earthquake.site.forms;

/**
 * Created by fly on 2016/10/14.
 */
public enum Status {
    SUCCESS(1), FAIL(0);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
