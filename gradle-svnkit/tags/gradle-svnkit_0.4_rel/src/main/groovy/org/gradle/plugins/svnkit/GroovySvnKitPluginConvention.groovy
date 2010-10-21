/*
 * Copyright 2010 Walter Di Carlo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.gradle.plugins.svnkit

class GroovySvnKitPluginConvention {
	String svn_root = null
	String svn_project = null
	String svn_trunk = "trunk"
	String svn_branches = "branches"
	String svn_tags = "tags"
	String svn_message = null
	String svn_username = ""
	String svn_password = "" // TODO: do not store the password
	String svn_branch_name
	String svn_tag_name
	
	  def svnkit(Closure closure) {
		  closure.delegate = this
		  closure()
	  }
	
}
