package org.cavedon.takehome.ui.starships

import org.cavedon.takehome.model.Starship
import org.cavedon.takehome.ui.DisplayItem

class StarshipDisplayItem(private val starship: Starship) : DisplayItem {
    override fun getTitle() = starship.name

    override fun getSubtitle() = "${starship.model} - ${starship.length} m"
}