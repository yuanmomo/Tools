/** Copyright (c) 2013 MoMo, yuanhb@fusionskye.com All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * Project Name : Tools
 * Package Name : net.yuanmomo.tools.path
 * Created on   : Jun 20, 20132:45:30 PM
 * File Name    : PathTool.java
 *
 * Author       : yuanmomo
 * Blog         : yuanmomo.net
 * Company      : 北京华青融天技术有限责任公司  
 */

package net.yuanmomo.tools.util.path;

import java.io.File;
import java.net.URI;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName : PathTool 
 * Function  : TODO ADD FUNCTION. 
 * Reason    : TODO ADD REASON. 
 * Date      : Jun 20, 2013 2:45:30 PM 
 *
 * @author   : MoMo
 * @version  
 * @since      JDK 1.6
 * @see 	 
 */
public class PathUtil {
	private static Logger logger=LoggerFactory.getLogger(PathUtil.class);
	
	public static URI getURIFromPath(String path) throws Exception{
		if(path==null || "".equals(path)){
			logger.error("Path of "+ path +" is null or blank, return null");
			throw new Exception("Path of "+ path +" is null or blank, return null");
		}
		if(new File(path).exists()){
			URI uri=new File(path).toURI();
			logger.info("File exists, convert to URI :"+ uri);
			return uri;
		}
		if(!path.startsWith("file:/")){
			URL url=Thread.currentThread().getContextClassLoader().getResource(path);
			if(url==null){
				logger.error("Incrroct path "+ path +", please check it.");
				throw new Exception("Incrroct path "+ path +", please check it.");
			}
			return url.toURI();
		}
		return new URI(path);
	}
}
