package utilities;

public class LawOfCosines {
	
	public float lawOfCosines(float a, float b, float c) {
		float angle;
		angle = (float) Math.cos(c);
		return (float)Math.sqrt((a * a) +
            (b * b) - 2 * a * b * angle);
	}
}
