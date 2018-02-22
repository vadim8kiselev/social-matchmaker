package com.kiselev.matchmaker.view.serialize.implementation.xml.model;

import com.kiselev.matchmaker.api.model.Entity;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "entities")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntitiesList<Pojo extends Entity> {

    @XmlElements({
            @XmlElement(name = "user", type = User.class),
            @XmlElement(name = "post", type = Post.class),
            @XmlElement(name = "group", type = Group.class)
    })
    private List<Pojo> entityList;
}
