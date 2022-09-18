package org.cavedon.takehome.ui.people

import org.cavedon.takehome.model.People
import org.cavedon.takehome.ui.DisplayItem

class PeopleDisplayItem(private val people: People) : DisplayItem {
    override fun getTitle() = people.name

    override fun getSubtitle() = "${people.height} cm - ${people.mass} kg"
}