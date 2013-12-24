/**
 * 
 */
package net.yuanmomo.tools.util.properties.bean;

/**
 * @author MoMo
 *
 */
public class Bean {
	private String name;
	
	private int a_int ;
	private Integer b_Integer ;
	
	private long c_long;
	private Long d_Long;
	
	private boolean e_boolean;
	private Boolean f_Boolean;
	
	private char g_char;
	private Character h_Character;
	
	private byte j_byte;
	private Byte k_Byte;
	
	private short l_short;
	private Short L_Short;
	
	private float m_float;
	private Float n_Float;
	
	private double o_double;
	private Double p_Double;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the a_int
	 */
	public int getA_int() {
		return a_int;
	}

	/**
	 * @param a_int the a_int to set
	 */
	public void setA_int(int a_int) {
		this.a_int = a_int;
	}

	/**
	 * @return the b_Integer
	 */
	public Integer getB_Integer() {
		return b_Integer;
	}

	/**
	 * @param b_Integer the b_Integer to set
	 */
	public void setB_Integer(Integer b_Integer) {
		this.b_Integer = b_Integer;
	}

	/**
	 * @return the c_long
	 */
	public long getC_long() {
		return c_long;
	}

	/**
	 * @param c_long the c_long to set
	 */
	public void setC_long(long c_long) {
		this.c_long = c_long;
	}

	/**
	 * @return the d_Long
	 */
	public Long getD_Long() {
		return d_Long;
	}

	/**
	 * @param d_Long the d_Long to set
	 */
	public void setD_Long(Long d_Long) {
		this.d_Long = d_Long;
	}

	/**
	 * @return the e_boolean
	 */
	public boolean isE_boolean() {
		return e_boolean;
	}

	/**
	 * @param e_boolean the e_boolean to set
	 */
	public void setE_boolean(boolean e_boolean) {
		this.e_boolean = e_boolean;
	}

	/**
	 * @return the f_Boolean
	 */
	public Boolean getF_Boolean() {
		return f_Boolean;
	}

	/**
	 * @param f_Boolean the f_Boolean to set
	 */
	public void setF_Boolean(Boolean f_Boolean) {
		this.f_Boolean = f_Boolean;
	}

	/**
	 * @return the g_char
	 */
	public char getG_char() {
		return g_char;
	}

	/**
	 * @param g_char the g_char to set
	 */
	public void setG_char(char g_char) {
		this.g_char = g_char;
	}

	/**
	 * @return the h_Character
	 */
	public Character getH_Character() {
		return h_Character;
	}

	/**
	 * @param h_Character the h_Character to set
	 */
	public void setH_Character(Character h_Character) {
		this.h_Character = h_Character;
	}

	/**
	 * @return the j_byte
	 */
	public byte getJ_byte() {
		return j_byte;
	}

	/**
	 * @param j_byte the j_byte to set
	 */
	public void setJ_byte(byte j_byte) {
		this.j_byte = j_byte;
	}

	/**
	 * @return the k_Byte
	 */
	public Byte getK_Byte() {
		return k_Byte;
	}

	/**
	 * @param k_Byte the k_Byte to set
	 */
	public void setK_Byte(Byte k_Byte) {
		this.k_Byte = k_Byte;
	}

	/**
	 * @return the l_short
	 */
	public short getL_short() {
		return l_short;
	}

	/**
	 * @param l_short the l_short to set
	 */
	public void setL_short(short l_short) {
		this.l_short = l_short;
	}

	/**
	 * @return the l_Short
	 */
	public Short getL_Short() {
		return L_Short;
	}

	/**
	 * @param l_Short the l_Short to set
	 */
	public void setL_Short(Short l_Short) {
		L_Short = l_Short;
	}

	/**
	 * @return the m_float
	 */
	public float getM_float() {
		return m_float;
	}

	/**
	 * @param m_float the m_float to set
	 */
	public void setM_float(float m_float) {
		this.m_float = m_float;
	}

	/**
	 * @return the n_Float
	 */
	public Float getN_Float() {
		return n_Float;
	}

	/**
	 * @param n_Float the n_Float to set
	 */
	public void setN_Float(Float n_Float) {
		this.n_Float = n_Float;
	}

	/**
	 * @return the o_double
	 */
	public double getO_double() {
		return o_double;
	}

	/**
	 * @param o_double the o_double to set
	 */
	public void setO_double(double o_double) {
		this.o_double = o_double;
	}

	/**
	 * @return the p_Double
	 */
	public Double getP_Double() {
		return p_Double;
	}

	/**
	 * @param p_Double the p_Double to set
	 */
	public void setP_Double(Double p_Double) {
		this.p_Double = p_Double;
	}

	@Override
	public String toString() {
		return "Bean [name=" + name + ", a_int=" + a_int + ", b_Integer="
				+ b_Integer + ", c_long=" + c_long + ", d_Long=" + d_Long
				+ ", e_boolean=" + e_boolean + ", f_Boolean=" + f_Boolean
				+ ", g_char=" + g_char + ", h_Character=" + h_Character
				+ ", j_byte=" + j_byte + ", k_Byte=" + k_Byte + ", l_short="
				+ l_short + ", L_Short=" + L_Short + ", m_float=" + m_float
				+ ", n_Float=" + n_Float + ", o_double=" + o_double
				+ ", p_Double=" + p_Double + "]";
	}
}
