/**
 * File Name: RandomUtil.java 
 * Created : Dec 4, 2016
 *
 * Microsoft JDBC Driver for SQL Server
 * The MIT License (MIT)
 * Copyright(c) 2016 Microsoft Corporation
 * All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *  
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR 
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.microsoft.sqlserver.testframework.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author Microsoft
 *
 */
public class RandomUtil {
	public static Random random = new Random();

	/**
	 * 
	 * @param prefix		Prefix
	 * @param maxLength		max length
	 * @param unique		Includes UUID.	
	 * @param isDatabase	Do you want for db name. 
	 * @return
	 */
	public String getIdentifier(String prefix, int maxLength, boolean unique, boolean isDatabase) {
		String identifier;
		StringBuilder sb = new StringBuilder();
		sb.append(prefix);
		sb.append("_");
		sb.append("jdbc_");
		sb.append(System.getProperty("user.name"));
		sb.append("_");
		if (unique) {
			//Create UUID.
			sb.append(UUID.randomUUID().toString());
		}

		identifier = sb.toString();

		if (maxLength < identifier.length()) {
			identifier = identifier.substring(0, maxLength);
		}
		return identifier;
	}

	/**
	 * Get Identifier for DB Name. 
	 * @param prefix		Prefix
	 * @param maxLength		max length
	 * @param unique		Includes UUID.	
	 * @return
	 */
	public String getIdentifierForDB(String prefix, int maxLength, boolean unique) {
		String identifier = getIdentifier(prefix, maxLength, unique, true);

		return removeInvalidDBChars(identifier);
	}
	
	/**
	 * 
	 * @param prefix		Prefix
	 * @param maxLength		max length
	 * @param isDatabase	Do you want for db name. 
	 * @return
	 */
	public String getUniqueIdentifier(String prefix, int maxLength, boolean isDatabase) {
		return getIdentifier(prefix, maxLength, true, isDatabase);
	}

	/**
	 * Remove Invalid DB Chars.  
	 * @param s
	 * @return
	 */
	private String removeInvalidDBChars(String s) {
		return s.replaceAll("[:-]", "_");
	}
	
	public Integer getMaxInteger() {
		return new Integer(Integer.MAX_VALUE);
	}
	
	public Integer getMinInteger() {
		return new Integer(Integer.MIN_VALUE);
	}
	
	
}
