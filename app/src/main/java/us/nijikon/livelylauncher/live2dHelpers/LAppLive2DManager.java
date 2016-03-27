/**
 *  modified by bowang
 */
package us.nijikon.livelylauncher.live2dHelpers;

import android.app.Activity;
import android.app.Application;
import android.app.LauncherActivity;
import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import jp.live2d.Live2D;
import jp.live2d.framework.L2DViewMatrix;
import jp.live2d.framework.Live2DFramework;
import us.nijikon.livelylauncher.launcher.Launcher;


public class LAppLive2DManager
{
	
	static public final String TAG = "SampleLive2DManager";

	private LAppView 				view;						

	
	private LAppModel model;


	
//	private int 					modelCount		=-1;
	private boolean 				reloadFlg;



	public LAppLive2DManager()
	{
		Live2D.init();
		Live2DFramework.setPlatformManager(new PlatformManager());
		model = new LAppModel();

	}


	public void releaseModel()
	{
//		for(int i=0;i<models.size();i++)
//		{
//			models.get(i).release();
//		}
		model.release();
//		models.clear();
	}


	
	public void update(GL10 gl) {
		view.update();
		if (reloadFlg){
            reloadFlg = false;
			try {
				model.load(gl, LAppDefine.MODEL_EPSILON);
			} catch (Exception e) {
				System.out.print("ERRRRRRRRRRRRR");
			}
	    }

//		if(reloadFlg)
//		{
//
//			reloadFlg=false;
//
//			int no = modelCount % 4;
//
//			try {
//				switch (no) {
//				case 0:
//					releaseModel();
//
//					models.add(new LAppModel());
//					models.get(0).load(gl, LAppDefine.MODEL_HARU);
//					models.get(0).feedIn();
//					break;
//				case 1:
//					releaseModel();
//
//					models.add(new LAppModel());
//					models.get(0).load(gl, LAppDefine.MODEL_SHIZUKU);
//					models.get(0).feedIn();
//					break;
//				case 2:
//					releaseModel();
//
//					models.add(new LAppModel());
//					models.get(0).load(gl, LAppDefine.MODEL_WANKO);
//					models.get(0).feedIn();
//					break;
//				case 3:
//					releaseModel();
//
//					models.add(new LAppModel());
//					models.get(0).load(gl, LAppDefine.MODEL_HARU_A);
//					models.get(0).feedIn();
//
//					models.add(new LAppModel());
//					models.get(1).load(gl, LAppDefine.MODEL_HARU_B);
//					models.get(1).feedIn();
//					break;
//				default:
//
//					break;
//				}
//			} catch (Exception e) {
//
//				Log.e(TAG, "Failed to load." + e.getStackTrace();
//				exit();
//			}
//		}
	}


	
//	public LAppModel getModel(int no)
    public LAppModel getModel()
	{
//		if(no>=models.size())return null;
//		return models.get(no);
		return model;
	}


//	public int getModelNum()
//	{
//		return models.size();
//	}


	//=========================================================
	
	//=========================================================
	
	public LAppView  createView(Activity act)
	{
		
		view = new LAppView( act ) ;
		view.setLive2DManager(this);
		view.startAccel(act);
		return view ;
	}


	
	public void onResume()
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "onResume");
		view.onResume();
	}


	
	public void onPause()
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "onPause");
		view.onPause();
	}


	
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "onSurfaceChanged " + width + " " + height);
		view.setupView(width,height);
		changeModel();
//		if(getModelNum()==0)
//		{
//
//			changeModel();
//
//		}
	}


	//=========================================================
	
	//=========================================================
	
	public void changeModel()
	{
		reloadFlg=true;

	}


	//=========================================================
	
	//=========================================================
	
	public boolean tapEvent(float x,float y)
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "tapEvent view x:" + x + " y:" + y);
//		model.startRandomMotion(LAppDefine.MOTION_NULL,LAppDefine.PRIORITY_NORMAL);
//		for (int i=0; i<models.size(); i++)
//		{
//			if(models.get(i).hitTest(  LAppDefine.HIT_AREA_HEAD,x, y ))
//			{
//
//				if(LAppDefine.DEBUG_LOG) Log.d(TAG, "Tap face.");
//				models.get(i).setRandomExpression();
//			}
//			else if(models.get(i).hitTest( LAppDefine.HIT_AREA_BODY,x, y))
//			{
//				if(LAppDefine.DEBUG_LOG) Log.d(TAG, "Tap body.");
////				models.get(i).startRandomMotion(LAppDefine.MOTION_GROUP_TAP_BODY, LAppDefine.PRIORITY_NORMAL );
//				models.get(i).startRandomMotion(LAppDefine.MOTION_NULL, LAppDefine.PRIORITY_NORMAL );
//			}
//		}
		return true;
	}


	
	public void flickEvent(float x,float y)
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "flick x:" + x + " y:" + y);
		if(model.hitTest(LAppDefine.HIT_AREA_HEAD,x,y)){
			model.startRandomMotion(LAppDefine.MOTION_NULL,LAppDefine.PRIORITY_NORMAL);
		}
//		for (int i=0; i<models.size(); i++)
//		{
//			if(models.get(i).hitTest( LAppDefine.HIT_AREA_HEAD, x, y ))
//			{
//				if(LAppDefine.DEBUG_LOG) Log.d(TAG, "Flick head.");
////				models.get(i).startRandomMotion(LAppDefine.MOTION_GROUP_FLICK_HEAD, LAppDefine.PRIORITY_NORMAL );
//				models.get(i).startRandomMotion(LAppDefine.MOTION_NULL, LAppDefine.PRIORITY_NORMAL );
//			}
//		}
	}


	
	public void maxScaleEvent()
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "Max scale event.");
		model.startRandomMotion(LAppDefine.MOTION_NULL,LAppDefine.PRIORITY_NORMAL);
//		for (int i=0; i<models.size(); i++)
//		{
////			models.get(i).startRandomMotion(LAppDefine.MOTION_GROUP_PINCH_IN,LAppDefine.PRIORITY_NORMAL );
//			models.get(i).startRandomMotion(LAppDefine.MOTION_NULL, LAppDefine.PRIORITY_NORMAL );
//		}
	}


	
	public void minScaleEvent()
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "Min scale event.");
		model.startRandomMotion(LAppDefine.MOTION_NULL,LAppDefine.PRIORITY_NORMAL);
//		for (int i=0; i<models.size(); i++)
//		{
////			models.get(i).startRandomMotion(LAppDefine.MOTION_GROUP_PINCH_OUT,LAppDefine.PRIORITY_NORMAL );
//			models.get(i).startRandomMotion(LAppDefine.MOTION_NULL, LAppDefine.PRIORITY_NORMAL );
//		}
	}


	
	public void shakeEvent()
	{
		if(LAppDefine.DEBUG_LOG) Log.d(TAG, "Shake event.");
		model.startRandomMotion(LAppDefine.MOTION_NULL,LAppDefine.PRIORITY_FORCE);
//		for (int i=0; i<models.size(); i++)
//		{
////			models.get(i).startRandomMotion(LAppDefine.MOTION_GROUP_SHAKE,LAppDefine.PRIORITY_FORCE );
//			models.get(i).startRandomMotion(LAppDefine.MOTION_NULL, LAppDefine.PRIORITY_NORMAL );
//		}
	}


	public void setAccel(float x,float y,float z)
	{
//		for (int i=0; i<models.size(); i++)
//		{
//			models.get(i).setAccel(x, y, z);
//		}
		model.setAccel(x,y,z);
	}


	public void setDrag(float x,float y)
	{
//		for (int i=0; i<models.size(); i++)
//		{
//			models.get(i).setDrag(x, y);
//		}
		model.setDrag(x,y);
	}


	public L2DViewMatrix getViewMatrix()
	{
		return view.getViewMatrix();
	}
}