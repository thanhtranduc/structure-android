/**
 * Copyright 2010 Per-Erik Bergman (per-erik.bergman@jayway.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.thanh.tran.structure;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.thanh.tran.structure.mesh.SimplePlane;

/**
 * This class is the setup for the Tutorial part VI located at:
 * http://blog.jayway.com/
 *
 * @author Per-Erik Bergman (per-erik.bergman@jayway.com)
 */
public class TutorialPartVI extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
        }
        // Remove the title bar from the window.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Make the windows into full screen mode.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a OpenGL view.
        GLSurfaceView view = new GLSurfaceView(this);

        // Creating and attaching the renderer.
        OpenGLRenderer renderer = new OpenGLRenderer();
        view.setRenderer(renderer);
        setContentView(view);

        // Create a new plane.
        SimplePlane plane = new SimplePlane(1, 1);

        // Move and rotate the plane.
        plane.z = 1.7f;
        plane.rx = -65;

        // Load the texture.
        plane.loadBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.demo));

        // Add the plane to the renderer.
        renderer.addMesh(plane);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}