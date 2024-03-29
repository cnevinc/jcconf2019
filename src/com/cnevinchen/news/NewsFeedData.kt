package com.cnevinchen.news
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text


@Root(strict = false)
class Rss {
    @field:Attribute
    var version: String? = null

    @field:Path("channel")
    @field:ElementList(name = "item", entry = "item", type = GoogleFeedItem::class, required = true, inline = true)
    var feedItems: List<GoogleFeedItem>? = null
}

open class FeedItem {
    open var pubDate: String? = ""

    open var title: String? = ""

    open var link: String? = ""

    open var description: String? = ""

    open var image: String? = ""

    open var source: String = ""

}

@Root(name = "item", strict = false)
class LiputanFeedItem @JvmOverloads constructor(

    @field:Element
    override var pubDate: String? = "",

    @field:Path("title")
    @field:Text(data = true)
    override var title: String? = "",

    @field:Element
    override var link: String? = "",

    @field:Path("description")
    @field:Text(data = true)
    override var description: String? = "",

    @field:Path("media:thumbnail")
    @field:Attribute(name = "url", required = false)
    override var image: String? = "",

    override var source: String = "liputan6"
) : FeedItem()


@Root(name = "item", strict = false)
class GoogleFeedItem @JvmOverloads constructor(

    @field:Element
    override var pubDate: String? = "",

    @field:Path("title")
    @field:Text(data = true)
    override var title: String? = "",

    @field:Element
    override var link: String? = "",

    @field:Path("description")
    @field:Text(data = true)
    override var description: String? = "",

    @field:Path("media:content")
    @field:Attribute(name = "url", required = false)
    override var image: String? = "",

    @field:Element(name = "source")
    override var source: String = ""
) : FeedItem()