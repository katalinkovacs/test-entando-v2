/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps;


import org.entando.entando.plugins.jpattendee.JpattendeeConfigTestUtils;

import com.agiletec.ConfigTestUtils;
import com.agiletec.aps.BaseTestCase;

public class JpattendeeBaseTestCase extends BaseTestCase {

	@Override
	protected ConfigTestUtils getConfigUtils() {
		return new JpattendeeConfigTestUtils();
	}

	
}
