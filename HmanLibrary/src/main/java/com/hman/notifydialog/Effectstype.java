package com.hman.notifydialog;

import com.hman.notifydialog.effects.BaseEffects;
import com.hman.notifydialog.effects.FadeIn;
import com.hman.notifydialog.effects.Fall;
import com.hman.notifydialog.effects.FlipH;
import com.hman.notifydialog.effects.FlipV;
import com.hman.notifydialog.effects.NewsPaper;
import com.hman.notifydialog.effects.RotateBottom;
import com.hman.notifydialog.effects.RotateLeft;
import com.hman.notifydialog.effects.Shake;
import com.hman.notifydialog.effects.SideFall;
import com.hman.notifydialog.effects.SlideBottom;
import com.hman.notifydialog.effects.SlideLeft;
import com.hman.notifydialog.effects.SlideRight;
import com.hman.notifydialog.effects.SlideTop;
import com.hman.notifydialog.effects.Slit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Copyright 2014 litao
 * https://github.com/sd6352051/NiftyDialogEffects
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public enum  Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }

    public List<Effectstype> list() {
        List<Effectstype> effList = new ArrayList<Effectstype>();
        effList.add(Fadein);
        effList.add(Slideleft);
        effList.add(Slidetop);
        effList.add(SlideBottom);
        effList.add(Slideright);
        effList.add(Fall);
        effList.add(Newspager);
        effList.add(Fliph);
        effList.add(Flipv);
        effList.add(RotateBottom);
        effList.add(RotateLeft);
        effList.add(Slit);
        effList.add(Shake);
        effList.add(Sidefill);
        return effList;
    }

    class RandomEnum<E extends Enum<Effectstype>> {
        Random RND = new Random();
        E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }

    public Effectstype radomEff() {
        RandomEnum<Effectstype> r = new RandomEnum<Effectstype>(Effectstype.class);
        System.out.println(r.random());
        return r.random();
    }
 }
