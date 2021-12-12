package glossar;


import org.schema.schine.graphicsengine.core.MouseEvent;
import org.schema.schine.graphicsengine.forms.gui.*;
import org.schema.schine.input.InputState;

import javax.vecmath.Vector4f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * STARMADE MOD
 * CREATOR: Max1M
 * DATE: 05.12.2021
 * TIME: 21:11
 */
class GlossarPageList extends GUIAncor {
    public GlossarPageList(float v, float v1, InputState inputState) {
        super(inputState, v, v1);

    }
    private static boolean sort;
    public static void addGlossarCat(GlossarCategory cat) {
        synchronized (categories) {
            categories.add(cat);
            sort = true;
        }
    }

    private static final ArrayList<GlossarCategory> categories = new ArrayList<>();
    private static GlossarEntry selected;
    public static GlossarEntry getSelected() {
        return selected;
    }
    private boolean firstDraw = true;
    @Override
    public void draw() {
        synchronized (categories) {
            if (sort) {
                Collections.sort(categories, new Comparator<GlossarCategory>() {
                    @Override
                    public int compare(GlossarCategory o1, GlossarCategory o2) {
                        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                    }
                });
                sort = false;
            }
        }

        if (firstDraw) {
            onInit();
            firstDraw = false;
        }
        super.draw();

    }

    boolean init = true;
    @Override
    public void onInit() {
        if (init) {
            init = false; //TODO one time adding of stuff
        }
        if (selected == null && categories.size()>0 && categories.get(0).getEntries().size()>0)
            selected = categories.get(0).getEntries().get(0);

        int border = (int)(getWidth() * 0.005f);

        //scrollable clickable list of mod pages
        GUIScrollablePanel pageOverview = new GUIScrollablePanel(getWidth()*0.275f-2*border,getHeight()-border, getState());
        pageOverview.setPos(border,border,0);

       GUIElementList pageList = new GUIElementList(getState());
        for (GlossarCategory cat: categories) {

            //each category
            GUITextOverlay text = new GUITextOverlay((int) pageOverview.getWidth(),-1,GlossarControlManager.titleFont,getState());
            text.setTextSimple(cat.getName());
            text.updateTextSize();
            text.setHeight(text.getTextHeight());
            pageList.add(new GUIListElement(text,getState()));

            //add all entries of this cat
            for (final GlossarEntry kitten: cat.getEntries()) {
                GUIColoredRectangle back = new GUIColoredRectangle(getState(),pageOverview.getWidth(),10, new Vector4f(1,0,0,0));

                GUITextOverlay textX = new GUITextOverlay((int) pageOverview.getWidth(),-1,GlossarControlManager.textFont,getState());
                textX.setTextSimple("   "+kitten.getTitle());
                textX.updateTextSize();
                textX.setHeight(text.getTextHeight());
                back.setHeight(textX.getHeight());
                back.attach(textX);


                GUITextButton b= new GUITextButton(getState(), (int)back.getWidth(),(int)back.getHeight(), "", new GUICallback() {
                    @Override
                    public void callback(GUIElement guiElement, MouseEvent mouseEvent) {
                        if (mouseEvent.pressedLeftMouse()) {
                            selected = kitten;
                        }
                    }

                    @Override
                    public boolean isOccluded() {
                        return false;
                    }
                });
                b.setPos(0,-0.5f*(back.getHeight()-textX.getTextHeight()),0);
                b.setColorPalette(GUITextButton.ColorPalette.TRANSPARENT);
                back.attach(b);
                pageList.add(new GUIListElement(back,getState()));
            }
        }

        pageList.updateDim();
        pageOverview.setContent(pageList);

        pageOverview.setScrollable(GUIScrollablePanel.SCROLLABLE_VERTICAL);

        GUIColoredRectangle pageOverviewBack = new GUIColoredRectangle(getState(),pageOverview.getWidth(),pageOverview.getHeight(),pageOverview,new Vector4f(1,0,0,0));
        pageOverviewBack.setPos(pageOverview.getPos());
        attach(pageOverviewBack);
        attach(pageOverview);

        //currently selected page with title and scrolalble textbox
        GlossarPage testpage = new GlossarPage(getState(),
                (int)(getWidth()-pageOverview.getWidth()-3*border), //TODO scroll bar occludes text behind it
                (int)getHeight()-border,
                "TEST","TEST");
        testpage.setPos(pageOverview.getWidth()+2*border,border,0);
        attach(testpage);
        super.onInit();

    }

    private void initCats() {
    //    categories.clear();
        for (int i = 0; i < 3; i++) {
            GlossarCategory cat = new GlossarCategory("TEST CATEGORY " + i);
            categories.add(cat);
            for (int j = 0; j < 3; j++) {
                cat.addEntry(new GlossarEntry("TEST TOPIC " + j,"owo"));
            }
        }
    }
}
