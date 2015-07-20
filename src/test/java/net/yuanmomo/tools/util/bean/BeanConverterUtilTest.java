/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package net.yuanmomo.tools.util.bean;

import org.junit.Test;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * </p>
 *
 * @author Hongbin Yuan
 * @date 2015年7月19日 下午1:07:33
 */


public class BeanConverterUtilTest {


	@Test
	public void test(){
		BeanDTOConverterUtil generator = new BeanDTOConverterUtil();

		System.out.println(generator.generate(A.class, ADTO.class, "to", "dtoTo"));

	}
	@Test
	public void testBeanCreateUtil(){
		try {
			System.out.println(BeanCreateUtil.generate(A.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

@Data
@NoArgsConstructor
class A{
	private int a;
	private byte b;
	private char c;
	private short d;
	private int e;
	private long f;
	private float g;
	private double h;

	private String i;
	private Object j;

	private Integer aExtra;
}


@Data
@NoArgsConstructor
class ADTO{
	public int a;
	public byte b;
	public char c;
	public short d;
	public int e;
	public long f;
	public float g;
	public double h;

	public String i;
	public Object j;

	public Integer aDTOExtra;
}