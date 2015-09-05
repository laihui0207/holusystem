package com.huivip.holu.webapp.util;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;

/**
 * Created by sunlaihui on 9/3/15.
 */
public class ComponentActionDecorator implements DisplaytagColumnDecorator {
    /*public String getSubComponentList(){
        Object obj=getCurrentRowObject();
        if(obj instanceof PaginatedListImpl){
            return "";
        }
        else if(obj instanceof Component){
            Component component=(Component)obj;
            if(component.getSubComponentListSet()!=null){
                return "<a href=\"<c:url value='/subComponentLists/${componentList.componentID}/subList'/> \">\n" +
                        "                <fmt:message key=\"component.detail\"/>\n" +
                        "            </a>";
            }
            else {
                return "<a href=\"<c:url value='/componentStyles/processlist?styleID=${componentList.styleID}&companyId=${componentList.project.company.companyId}&cid=${componentList.componentID}&type=parent'/>\">\n" +
                        "            <fmt:message key=\"action.processList\"/>\n" +
                        "        </a>";
            }
        }
        return "";
    }
*/
    @Override
    public Object decorate(Object dataValue, PageContext pageContext, MediaTypeEnum mediaTypeEnum) throws DecoratorException {

        return dataValue;
    }
}
