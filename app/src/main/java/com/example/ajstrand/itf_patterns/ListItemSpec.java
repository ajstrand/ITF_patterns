package com.example.ajstrand.itf_patterns;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Toast;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.Text;

import static com.facebook.yoga.YogaEdge.ALL;

/**
 * Created by ajstrand on 2/15/2018.
 */

@LayoutSpec
public class ListItemSpec {

    @OnEvent(ClickEvent.class)
    static void onClick(ComponentContext c, @FromEvent View view,
                        @Prop int id,
                        @Prop boolean twoPane,
    @Prop FragmentManager manage) {
        //ListItemSpec.updateState(isPressed);
        //Toast.makeText(c, ""+isPressed.get(), Toast.LENGTH_SHORT).show();
        if (twoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(PatternDetailFragment.ARG_ITEM_ID, id);
            PatternDetailFragment fragment = new PatternDetailFragment();
            fragment.setArguments(arguments);
            manage.beginTransaction()
                    .replace(R.id.pattern_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(c, PatternDetailActivity.class);
            intent.putExtra(PatternDetailFragment.ARG_ITEM_ID, id);

            c.startActivity(intent);
        }
    }

    /*@OnUpdateState
    static void updateState(@State StateValue<Boolean> isPressed) {
        isPressed.set(!isPressed.get());    }*/

    @OnCreateLayout
    static Component onCreateLayout(ComponentContext c,
                                    //@State StateValue<Boolean> isPressed,
                                    @Prop String title) {

        return Column.create(c)
                .clickHandler(ListItem.onClick(c))
                .paddingDip(ALL, 16)
                .backgroundColor(Color.WHITE)
                .child(
                        Text.create(c)
                                .text(title)
                                .textSizeSp(30))
                .child(
                        Text.create(c)
                                .text("Click the item to view more info")
                                .textSizeSp(20))
                .build();
    }
}
