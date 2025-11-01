package com.example.exploreboston.data

data class Category(
    val id: String,
    val title: String,
    val blurb: String
)

data class Destination(
    val id: Int,
    val categoryId: String,
    val name: String,
    val headline: String,
    val description: String,
    val address: String
)

object TourData {
    val categories: List<Category> = listOf(
        Category(
            id = "museums",
            title = "Museums",
            blurb = "Boston's innovation, art, and science highlights."
        ),
        Category(
            id = "parks",
            title = "Parks & Outdoors",
            blurb = "Lush green escapes throughout the city."
        ),
        Category(
            id = "history",
            title = "Historic Sites",
            blurb = "Landmarks that shaped the American story."
        ),
        Category(
            id = "food",
            title = "Local Eats",
            blurb = "Distinct flavors from neighborhoods across Boston."
        )
    )

    private val destinations: List<Destination> = listOf(
        Destination(
            id = 1,
            categoryId = "museums",
            name = "MIT Museum",
            headline = "Hands-on tech and art exhibits",
            description = "The MIT Museum showcases interactive exhibits that celebrate the fusion of technology, science, and creativity. You will find everything from kinetic sculptures to cutting-edge robotics demonstrations.",
            address = "314 Main St, Cambridge, MA"
        ),
        Destination(
            id = 2,
            categoryId = "museums",
            name = "Museum of Fine Arts",
            headline = "World-class art collections",
            description = "One of the largest museums in the United States, the MFA houses a diverse collection covering ancient artifacts, European masterpieces, and contemporary art installations.",
            address = "465 Huntington Ave, Boston, MA"
        ),
        Destination(
            id = 3,
            categoryId = "parks",
            name = "Boston Public Garden",
            headline = "Swan boats and seasonal blooms",
            description = "Established in 1837, the Boston Public Garden invites visitors to stroll along winding paths, ride the iconic Swan Boats, and enjoy colorful flowerbeds year-round.",
            address = "4 Charles St, Boston, MA"
        ),
        Destination(
            id = 4,
            categoryId = "parks",
            name = "Charles River Esplanade",
            headline = "Skyline views along the river",
            description = "A popular destination for runners, cyclists, and kayakers, the Esplanade offers sweeping views of the Charles River and the Boston skyline, punctuated by public art and performance spaces.",
            address = "Storrow Dr, Boston, MA"
        ),
        Destination(
            id = 5,
            categoryId = "history",
            name = "Old North Church",
            headline = "Famous lanterns of the Revolution",
            description = "Dating back to 1723, Old North Church is the site where the iconic 'One if by land, and two if by sea' signal was sent. The church continues to share stories from Boston's revolutionary past.",
            address = "193 Salem St, Boston, MA"
        ),
        Destination(
            id = 6,
            categoryId = "history",
            name = "USS Constitution",
            headline = "America's oldest commissioned warship",
            description = "Explore the legendary USS Constitution, known as 'Old Ironsides', and the adjacent museum to learn how the ship earned its nickname and what life was like for sailors during the War of 1812.",
            address = "Building 5, Charlestown Navy Yard, Charlestown, MA"
        ),
        Destination(
            id = 7,
            categoryId = "food",
            name = "Quincy Market",
            headline = "Classic New England bites",
            description = "Quincy Market is packed with local vendors serving clam chowder, lobster rolls, and other regional staples. Street performers often bring extra energy to the bustling market halls.",
            address = "206 S Market St, Boston, MA"
        ),
        Destination(
            id = 8,
            categoryId = "food",
            name = "North End Cannoli Crawl",
            headline = "Sweet tour through Little Italy",
            description = "Taste your way through the North End by sampling cannoli from iconic bakeries like Mike's Pastry and Modern Pastry. Along the way you'll learn about the neighborhood's Italian heritage.",
            address = "Hanover St, Boston, MA"
        )
    )

    fun getCategory(categoryId: String): Category? =
        categories.find { it.id == categoryId }

    fun getDestinations(categoryId: String): List<Destination> =
        destinations.filter { it.categoryId == categoryId }

    fun getDestination(categoryId: String, locationId: Int): Destination? =
        destinations.firstOrNull { it.categoryId == categoryId && it.id == locationId }
}
