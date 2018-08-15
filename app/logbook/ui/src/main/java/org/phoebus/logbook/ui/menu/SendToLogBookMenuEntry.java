/*******************************************************************************
 * Copyright (c) 2018 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.phoebus.logbook.ui.menu;

import org.phoebus.framework.spi.MenuEntry;
import org.phoebus.framework.workbench.ApplicationService;
import org.phoebus.ui.javafx.ImageCache;

import javafx.scene.image.Image;

/**
 * MenuEntry for sending a log book entry outside of a context menu.
 * @author Evan Smith
 */
public class SendToLogBookMenuEntry implements MenuEntry
{
    @Override
    public Void call() throws Exception
    {
        ApplicationService.createInstance(SendToLogBookApp.NAME);
        return null;
    }

    @Override
    public String getName()
    {
        return SendToLogBookApp.DISPLAY_NAME;
    }

    @Override
    public String getMenuPath()
    {
        return "Utility";
    }
    
    @Override
    public Image getIcon()
    {
        return ImageCache.getImage(SendToLogBookMenuEntry.class, "/icons/logentry-add-16.png");
    }

}