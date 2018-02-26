package com.example.ajstrand.itf_patterns;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.SingleComponentSection;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by ajstrand on 2/24/2018.
 */
@GroupSectionSpec
public class PatternSectionSpec {

    @OnCreateChildren
    static Children onCreateChildren(SectionContext c,
                                     @Prop boolean twoPane,
    @Prop FragmentManager manage){
        List<ITF_Pattern.PatternItem> items = ITF_Pattern.ITEMS;

        Children.Builder builder = Children.create();
        for (int i = 0; i< items.size(); i++) {
            String test = items.get(i).title;
            int id = items.get(i).id;
            builder.child(SingleComponentSection.create(c)
                    .key(String.valueOf(i))
                    .component(ListItem.create(c)
                            .title(test)
                            .twoPane(twoPane)
                            .id(id)
                            .manage(manage)
                            .build()));
        }
        return builder.build();
    }
}
