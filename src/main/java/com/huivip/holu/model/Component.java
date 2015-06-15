package com.huivip.holu.model;

/**
 * Created by sunlaihui on 6/15/15.
 * SELECT TOP 1000 [ID]
 ,[ComponentID]
 ,[ProjectID]
 ,[ComponentName]
 ,[Size]
 ,[Material]
 ,[Length]
 ,[Quantity]
 ,[Weight]
 ,[Price]
 ,[StyleName]
 ,[PieceList]
 ,[UserID]
 ,[CreateDate]
 FROM [MidDatabase].[dbo].[U_HOLU_ComponentList]
 */
public class Component {
    Long id;
    String componentID;
    Project project;
    String componetName;
    int size;

}
