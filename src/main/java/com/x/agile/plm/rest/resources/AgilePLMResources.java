package com.x.agile.plm.rest.resources;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.json.JSONObject;

import com.x.agile.plm.rest.bean.AttachmentBean;
import com.x.agile.plm.rest.exception.CustomException;
import com.x.agile.plm.rest.service.AgilePLMServiceImpl;

@XmlRootElement
@Path("/")
public class AgilePLMResources {

	/**
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws IOException
	 *             returns the Agile attachment based on passed parameter
	 */
	@GET
	@Path("AgileDocAttachment")
	@Produces("application/octet-stream")
	@XmlValue
	public Response getAgileDocAttachment(@QueryParam("itemName") String itemName,
			@DefaultValue("AttachmentName.txt")@QueryParam("fileName") String fileName, @DefaultValue("RedLine")@QueryParam("fileDesc") String fileDesc) throws IOException {

		AttachmentBean attBO = null;
		AgilePLMServiceImpl obj = new AgilePLMServiceImpl();
		try {
			if(itemName == null || fileName == null)
				throw new CustomException("Item Name/Attachment Name is Missing");
			else {
				obj.init();
				attBO = obj.getAttachment(itemName, fileName, fileDesc);
				if (attBO != null) {
					return Response.ok(attBO.getAttStream())
							.header("Content-Disposition", "attachment; filename=\"" + attBO.getAttName() + "\"") // optional
							.build();
				} else {
					throw new CustomException("No attachment available");
				}
			}
		} catch (Exception e) {
			JSONObject jObj = new org.json.JSONObject();
			jObj.put("Error", e.getMessage());
			return Response.status(Response.Status.SEE_OTHER).entity(jObj.toString()).build();
		} 
	}

}