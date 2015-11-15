package net.paoding.rose.web.instruction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.paoding.rose.web.Invocation;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class TextInstruction extends AbstractInstruction {
	protected static Log logger = LogFactory.getLog(TextInstruction.class);
	private String text;

	public String text() {
		return this.text;
	}

	public TextInstruction text(String text) {
		this.text = text;
		return this;
	}

	public void doRender(Invocation inv) throws Exception {
		if (StringUtils.isEmpty(this.text)) {
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("trying to render text:" + this.text);
		}
		HttpServletResponse response = inv.getResponse();
		String oldEncoding = response.getCharacterEncoding();
		if ((StringUtils.isBlank(oldEncoding))
				|| (oldEncoding.startsWith("ISO-"))) {
			String encoding = inv.getRequest().getCharacterEncoding();
			Assert.isTrue(encoding != null);
			response.setCharacterEncoding(encoding);
			if (logger.isDebugEnabled()) {
				logger.debug("set response.characterEncoding by default:"
						+ response.getCharacterEncoding());
			}
		}
		String mainContentType = null;
		boolean find = false;
		int contentTypeIndex = this.text.indexOf(':');
		int mainContentTypeIndex = -1;
		if (contentTypeIndex > 0) {
			mainContentTypeIndex = this.text.indexOf(";");
			if ((mainContentTypeIndex < 0)
					|| (mainContentTypeIndex > contentTypeIndex)) {
				mainContentTypeIndex = contentTypeIndex;
				find = true;
			}
			mainContentType = this.text.substring(0, mainContentTypeIndex);
			if (mainContentType.length() == 0) {
				mainContentType = "text/html";
			} else if (mainContentType.equals("json")) {
				mainContentType = "application/json";
			} else if (mainContentType.equals("html")) {
				mainContentType = "text/html";
			} else if (mainContentType.equals("xml")) {
				mainContentType = "text/xml";
			} else if ((mainContentType.equals("text"))
					|| (mainContentType.equals("plain"))) {
				mainContentType = "text/plain";
			} else if ((!mainContentType.startsWith("text/"))
					&& (!mainContentType.startsWith("application/"))) {
				if (logger.isDebugEnabled()) {
					logger.debug("'" + mainContentType
							+ "' is not a content-type, skip it! ");
				}
				mainContentType = null;
			}
		}
		if ((find) && (mainContentType != null)) {
			Assert.isTrue(contentTypeIndex > 0);
			String contentType;
			if (contentTypeIndex != mainContentTypeIndex) {
				contentType = mainContentType
						+ this.text.substring(mainContentTypeIndex,
								contentTypeIndex);
			} else {
				contentType = mainContentType;
			}
			response.setContentType(contentType);
			if (logger.isDebugEnabled()) {
				logger.debug("set response content-type: "
						+ response.getContentType());
			}
			sendResponse(response, this.text.substring(contentTypeIndex + 1));
		} else {
			if (response.getContentType() == null) {
				response.setContentType("text/html");
				if (logger.isDebugEnabled()) {
					logger.debug("set response content-type by default: "
							+ response.getContentType());
				}
			}
			sendResponse(response, this.text);
		}
	}

	private void sendResponse(HttpServletResponse response, String text)
			throws IOException {
		if (StringUtils.isNotEmpty(text)) {
			PrintWriter out = response.getWriter();
			if (logger.isDebugEnabled()) {
				logger.debug("write text to response:" + text);
			}
			out.print(text);
		}
	}

	public String toString() {
		return this.text;
	}
}