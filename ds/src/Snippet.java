

public class Snippet {
	public static void main(String[] args) {/*
		<?xml version="1.0" encoding="utf-8"?>
		<mx:Application xmlns:mx="http://www.adobe.com/2006/mxml" layout="absolute" creationComplete="formMenuBar();">
		
		<mx:Script>
		     <![CDATA[
		          import mx.collections.ArrayCollection;
		          import mx.controls.Alert;
		          import mx.controls.MenuBar;
		          
		          
		          
		          
		          
		          private function formMenuBar():void
		          {
		               var menuBar:MenuBar = new MenuBar();
		             menuBar.labelField = "@label";
		             var arrayCollection:ArrayCollection = new ArrayCollection();
		               
		               for each(var mainChild:XML in menuData )
		               {
		                    var objParent:Object = new Object();
		                    objParent.label = mainChild.attributes();
		                    objParent.status = "Parent";
		                    arrayCollection.addItem(objParent);
		                    generateXml(mainChild, arrayCollection, objParent);     
		               }
		               var menuBar:MenuBar = new MenuBar();
		               menuBar.labelField = "label";
		               menuBar.dataProvider = arrayCollection;
		               addChild(menuBar);
		               
		          }
		          
		          
		           
		          
		          
		
		
		          
		           
		          
		          private function generateXml(input:XML, array:ArrayCollection, obj:Object):void
		          {
		                    //Alert.show("input" + input.attributes());
		                    var children:XMLList = input.children();
		                    var parentNode:Object = null;
		                    for each(var child:XML in children)
		                    {
		                         if (child.children().length() != 0) {
		                              var objParent:Object = new Object();
		                              objParent.label = child.attributes();
		                              objParent.status = "Parent";
		                              if (obj.children != null )
		                              {
		                                   var arrColl:ArrayCollection = ArrayCollection(obj.children);
		                                   arrColl.addItem(objParent);
		                              }
		                              else
		                              {
		                                   var arrColl:ArrayCollection = new ArrayCollection();
		                                   arrColl.addItem(objParent);
		                                   obj.children = arrColl;
		                              }
		                              generateXml(child, array, objParent);
		                         }
		                         else
		                         {
		                              var objChild:Object = new Object();
		                              
		                              objChild.label = child.@label;
		                              Alert.show(objChild.label);
		                              if ( objChild.label == "Active Sessions"  || objChild.label == "Operator Configuration" || objChild.label == "Network Service Configuration")
		                              {
		                                   if (obj.children != null )
		                                   {
		                                        var arrColl:ArrayCollection = ArrayCollection(obj.children);
		                                        arrColl.addItem(objChild);
		                                   }
		                                   else
		                                   {
		                                        var arrColl:ArrayCollection = new ArrayCollection();
		                                        arrColl.addItem(objChild);
		                                        obj.children = arrColl;
		                                   }
		                              }
		                         }
		                         
		                    }
		          }
		          
		          
		          
		     ]]>
		</mx:Script>
		
		<mx:XMLList id="menuData">
		<menuitem label="Administration">
		        <menuitem label="Operator Configuration"/>
		        <menuitem label="Network Management Domains (NMDs)"/>
		        <menuitem label="Net Guardian Configuration"/>
		        <menuitem label="Tools">
		                <menuitem label="Hub Tools">
		                        <menuitem label="Active Sessions"/>
		                        <menuitem label="File Reconcilation"/>
		                        <menuitem label="Operator Logs"/>
		                        <menuitem label="Reports">
		                                <menuitem label="General Reports"/>
		                                <menuitem label="Hub Reports"/>
		                                <!--<menuitem label="Remote Reports"/>
		                                <menuitem label="Appliance Reports"/> -->
		                        </menuitem>
		                        <menuitem label="SDL Statistics"/>
		                        <menuitem label="Bulk Ranging"/>
		                        <!-- <menuitem label="Ranging History"/> -->
		                </menuitem>
		
		                <menuitem label="Monitor Tools">
		                        <menuitem label="Enable/Disable Device Polling"/>
		                        <menuitem label="Polling Timestamps"/>
		                </menuitem>
		        </menuitem>
		        <!-- <menuitem label="Virtual Network Configuration"/>
		        <menuitem label="Virtual Network List"/> -->
		</menuitem>
		
		<menuitem label="Service">
		    <menuitem label="Network Service Configuration"/>
		    <menuitem label="Service Manager"/>
		    <menuitem label="Element Manager"/>
		    <menuitem label="Satellite Router Creation"/>
		    <menuitem label="Satellite Router - Bulk Creation"/>
		</menuitem>
		
		<!-- <menuitem label="Help">
		        <menuitem label="About"/>
		        <menuitem label="Document Library"/>
		        <menuitem label="HX ExpertNMS Help"/>
		        <menuitem label="License Registration"/>
		        <menuitem label="Legend">
		                <menuitem label="Device Status Legend"/>
		                <menuitem label="Performance Status Legend"/>
		                <menuitem label="Satellite Router Pie Chart Legend"/>
		                <menuitem label="Satellite Router Status Legend"/>
		        </menuitem>
		</menuitem> -->
		</mx:XMLList>
		
		
		
		</mx:Application>
		
	*/}
}