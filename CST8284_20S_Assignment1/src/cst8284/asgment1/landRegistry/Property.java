package cst8284.asgment1.landRegistry;

/* 
 * Course Name: CST8284
 * Student Name:Brahim Toure
 * Class name: Property
 * Date:6/22/2020
*/
public class Property {
	private static final double TAX_RATE_PER_M2 = 12.50;
	private static final int DEFAULT_REGNUM = 999;
	private int xLeft;
	private int yTop;
	private int xLength;
	private int yWidth;
	private int regNum;
	private int area;
	private double taxes;

	public Property() {
		this(0, 0, 0, 0);
	}

	public Property(int xLength, int yWidth, int xLeft, int yTop) {
		this(xLength, yWidth, xLeft, yTop, DEFAULT_REGNUM);
	}

	public Property(Property prop, int regNum) {
		this(prop.getXLength(), prop.getYWidth(), prop.getXLeft(), prop.getYTop(), regNum);

	}

	public Property(int xLength, int yWidth, int xLeft, int yTop, int regNum) {
		setXLength(xLength);
		setYWidth(yWidth);
		setXleft(xLeft);
		setYTop(yTop);
		setRegNum(regNum);
	}

	public int getXLeft() {
		return xLeft;

	}

	public void setXleft(int left) {
		this.xLeft = left;

	}

	public int getXRight() {
		return getXLeft() + getXLength();

	}

	public int getYTop() {
		return yTop;

	}

	public void setYTop(int top) {
		this.yTop = top;
	}

	public int getYBottom() {
		return getYTop() + getYWidth();
	}

	public int getYWidth() {
		return yWidth;

	}

	public void setYWidth(int width) {
		this.yWidth = width;
	}

	public int getXLength() {
		return xLength;

	}

	public void setXLength(int length) {
		this.xLength = length;

	}

	public int getRegNum() {
		return regNum;
	}

	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}

	public int getArea() {
		area = getYWidth() * getXLength();
		return area;
	}

	public double getTaxes() {
		taxes = area * TAX_RATE_PER_M2;
		return taxes;

	}

	@Override
	public String toString() {
		return "Coordinates: " + getXLeft() + ", " + getYTop() + "\n" + "Length:" + getXLength() + " m" + "\t"
				+ "Width:" + getYWidth() + " m" + "\nRegistrant: #" + getRegNum() + "\nArea: " + getArea() + " m2"
				+ "\nProperty Taxes : $" + getTaxes();

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Registrant)) {
			return false;
		}
		Property s = (Property) obj;
		return getXLeft() == s.getXLeft() && getYTop() == s.getYTop() && getXLength() == s.getXLength()
				&& getYWidth() == s.getYWidth() && getArea() == s.getArea() && getTaxes() == s.getTaxes();

	}

	public boolean hasSameSides(Property prop) {
		return prop.getXLength() == this.getXLength() && prop.getYWidth() == this.getYWidth();
	}

	public boolean overLaps(Property prop) {
		if (prop.getYTop() >= getYBottom() || prop.getXLeft() >= getXRight() || prop.getXRight() <= getXLeft()
				|| prop.getYBottom() <= getYTop())
			return false;
		return true;
	}
}
