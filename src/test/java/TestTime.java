import java.util.Calendar;
import java.util.Date;

public class TestTime {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();

		calendar.add(Calendar.MINUTE, -5);

		Date compareTime = calendar.getTime();
		
		System.out.println(now);
		System.out.println(compareTime);
		System.out.println(now.after(compareTime));
	}
}
