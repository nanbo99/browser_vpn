/*
 * Copyright (c) 2006, Greg Hewett
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Greg Hewett nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
 
/*
 * Created on Aug 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

import com.strand3.proxy.ProxyHandler;
import com.strand3.proxy.ProxyHandlerFactory;

/**
 * @author grehewe
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BrowserVPNServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException
	{
	    ProxyHandler ph = null;
	    
	    try {
	        ph = ProxyHandlerFactory.getProxyHandler(req.getPathInfo());
		    //System.err.println("Using Class: " + ph.getClass().getName());
	    }
	    catch (Exception e) {
	        System.err.println("Error: " + e);
	        return;
	    }

	    ph.setScheme(req.getScheme());
		ph.setServerName(req.getServerName());
		ph.setServerPort(req.getServerPort());
		ph.setContextPath(req.getContextPath());
		ph.setServletPath(req.getServletPath());
		ph.doProxy(res.getOutputStream());
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException
	{
		doGet(req, res);
	}
}
