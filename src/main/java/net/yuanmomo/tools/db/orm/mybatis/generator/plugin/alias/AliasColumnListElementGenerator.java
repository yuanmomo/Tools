/** 
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.db.orm.mybatis.generator.plugin.alias
 * Created on   : 2013-12-25上午12:51:03
 * File Name    : BaseColumnListElementGenerator.java
 *
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */
/**
 * Project Name : Tools
 * File Name    : BaseColumnListElementGenerator.java
 * Package Name : net.yuanmomo.tools.db.orm.mybatis.generator.plugin.alias
 * Created on   : 2013-12-25上午12:51:03
 * Author       : Hongbin Yuan
 * Blog         : yuanmomo.net
 * Company      : 成都逗溜网科技有限公司  
 */

package net.yuanmomo.tools.db.orm.mybatis.generator.plugin.alias;

import java.util.Iterator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * ClassName : AliasColumnListElementGenerator 
 * Function : TODO ADD FUNCTION.
 * Reason : TODO ADD REASON. Date : 2013-12-25 上午12:51:03
 * 
 * @author : Hongbin Yuan
 * @version
 * @since JDK 1.6
 * @see
 */
public class AliasColumnListElementGenerator {
	private String resultMapId = "Alias_Column_List";
	
	/**
	 * addElements: 在当前的parentElement元素中添加别名列元素. <br/>
	 *
	 * @author Hongbin Yuan
	 * @param parentElement			父元素，新的elements将作为子元素添加到该元素
	 * @param introspectedTable		当前表的信息
	 * @since JDK 1.6
	 */
	public void addElements(XmlElement parentElement,IntrospectedTable introspectedTable) {
		XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id",resultMapId));

		StringBuilder sb = new StringBuilder();
		Iterator<IntrospectedColumn> iter = introspectedTable
				.getNonBLOBColumns().iterator();
		
		// 得到当前表的名字
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime().toUpperCase();
		while (iter.hasNext()) {
			IntrospectedColumn cloumn = iter.next();
			
			sb.append(tableName +"."+ MyBatis3FormattingUtilities.getSelectListPhrase(cloumn).toUpperCase());
			sb.append("\t\t\tAS\t\t").append(tableName +"_"+ MyBatis3FormattingUtilities.getSelectListPhrase(cloumn).toUpperCase());

			if (iter.hasNext()) {
				sb.append(", "); //$NON-NLS-1$
			}

			if (sb.length() > 80) {
				answer.addElement(new TextElement(sb.toString()));
				sb.setLength(0);
			}
		}

		if (sb.length() > 0) {
			answer.addElement((new TextElement(sb.toString())));
		}

		parentElement.addElement(answer);
	}
}
