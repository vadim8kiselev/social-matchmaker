package com.kiselev.matchmaker.api.network.vk.converter;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.entity.Group;
import com.kiselev.matchmaker.api.model.entity.Post;
import com.kiselev.matchmaker.api.model.entity.User;
import com.vk.api.sdk.objects.base.Geo;
import com.vk.api.sdk.objects.base.Place;
import com.vk.api.sdk.objects.groups.ContactsItem;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.LinksItem;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.polls.Answer;
import com.vk.api.sdk.objects.users.Career;
import com.vk.api.sdk.objects.users.Military;
import com.vk.api.sdk.objects.users.School;
import com.vk.api.sdk.objects.users.University;
import com.vk.api.sdk.objects.users.UserFull;
import com.vk.api.sdk.objects.wall.WallpostAttachment;
import com.vk.api.sdk.objects.wall.WallpostAttachmentType;
import com.vk.api.sdk.objects.wall.WallpostFull;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class VKEntityConverter implements EntityConverter<UserFull, WallpostFull, GroupFull> {

    @Override
    public User convertUser(UserFull externalUser) {
        return User.builder()
                .id(externalUser.getId().toString())
                .firstName(externalUser.getFirstName())
                .lastName(externalUser.getLastName())
                .screenName(nonNull(externalUser.getScreenName()))
                .sex(nonNull(externalUser.getSex()))
                .birthday(nonNull(externalUser.getBdate()))
                .online(String.valueOf(externalUser.isOnline() || externalUser.isOnlineMobile()))
                .photoLink(externalUser.getPhotoMaxOrig())

                .city(externalUser.getCity() != null ? externalUser.getCity().getTitle() : "")
                .homeTown(nonNull(externalUser.getHomeTown()))
                .country(externalUser.getCountry() != null ? externalUser.getCountry().getTitle() : "")

                .mobilePhone(nonNull(externalUser.getMobilePhone()))
                .homePhone(nonNull(externalUser.getHomePhone()))

                .skype(nonNull(externalUser.getSkype()))
                .facebook(nonNull(externalUser.getFacebook()))
                .twitter(nonNull(externalUser.getTwitter()))
                .livejournal(nonNull(externalUser.getLivejournal()))
                .instagram(nonNull(externalUser.getInstagram()))

                .status(nonNull(externalUser.getStatus()))
                .lastSeen(externalUser.getLastSeen() != null
                        ? new Date(externalUser.getLastSeen().getTime() * 1000L).toString()
                        : "")

                .career(listToString(nonNull(externalUser.getCareer())))
                .military(listToString(nonNull(externalUser.getMilitary())))
                .university(listToString(nonNull(externalUser.getUniversities())))
                .schools(listToString(nonNull(externalUser.getSchools())))

                .relation(VKPropertyMapper.getRelation(nonNull(externalUser.getRelation())))
                .relationPartner(externalUser.getRelationPartner() != null
                        ? externalUser.getRelationPartner().getFirstName() + " " + externalUser.getRelationPartner().getLastName()
                        : "")

                .interests(nonNull(externalUser.getInterests()))
                .music(nonNull(externalUser.getMusic()))
                .activities(nonNull(externalUser.getActivities()))
                .movies(nonNull(externalUser.getMovies()))
                .tv(nonNull(externalUser.getTv()))
                .books(nonNull(externalUser.getBooks()))
                .games(nonNull(externalUser.getGames()))
                .about(nonNull(externalUser.getAbout()))
                .quotes(nonNull(externalUser.getQuotes()))

                .political(externalUser.getPersonal() != null ? VKPropertyMapper.getPoliticalViews(nonNull(externalUser.getPersonal().getPolitical())) : "")
                .languages(externalUser.getPersonal() != null ? listToString(nonNull(externalUser.getPersonal().getLangs())) : "")
                .religion(externalUser.getPersonal() != null ? nonNull(externalUser.getPersonal().getReligion()) : "")
                .inspiredBy(externalUser.getPersonal() != null ? nonNull(externalUser.getPersonal().getInspiredBy()) : "")
                .peopleMain(externalUser.getPersonal() != null ? VKPropertyMapper.getImportantInOthers(nonNull(externalUser.getPersonal().getPeopleMain())) : "")
                .lifeMain(externalUser.getPersonal() != null ? VKPropertyMapper.getPersonalPriority(nonNull(externalUser.getPersonal().getLifeMain())) : "")
                .smoking(externalUser.getPersonal() != null ? VKPropertyMapper.getSmokingAndAlcohol(nonNull(externalUser.getPersonal().getSmoking())) : "")
                .alcohol(externalUser.getPersonal() != null ? VKPropertyMapper.getSmokingAndAlcohol(nonNull(externalUser.getPersonal().getAlcohol())) : "")

                .deactivated(nonNull(externalUser.getDeactivated()))
                .build();
    }

    @Override
    public List<User> convertUsers(List<UserFull> externalUsers) {
        return externalUsers.stream()
                .map(this::convertUser)
                .collect(Collectors.toList());
    }

    @Override
    public Post convertPost(WallpostFull externalPost) {
        return Post.builder()
                .id(externalPost.getOwnerId().toString() + "_" + externalPost.getId().toString())
                .fromId(externalPost.getFromId().toString())
                .ownerId(externalPost.getOwnerId().toString())
                .date(externalPost.getDate() != null
                        ? new Date(externalPost.getDate() * 1000L).toString()
                        : "")
                .text(externalPost.getText())

                .likesCount(externalPost.getLikes() != null ? externalPost.getLikes().getCount().toString() : "")
                .sharesCount(externalPost.getReposts() != null ? externalPost.getReposts().getCount().toString() : "")
                .viewsCount(externalPost.getViews() != null ? externalPost.getViews().getCount().toString() : "")
                .commentsCount(externalPost.getComments() != null ? externalPost.getComments().getCount().toString() : "")

                .geo(entityToString(externalPost.getGeo()))
                .type(externalPost.getPostType().getValue())
                .attachments(listToString(nonNull(externalPost.getAttachments())))
                .platform(nonNull(externalPost.getPostSource().getPlatform()))
                .build();
    }

    @Override
    public List<Post> convertPosts(List<WallpostFull> externalPosts) {
        return externalPosts.stream()
                .map(this::convertPost)
                .collect(Collectors.toList());
    }

    @Override
    public Group convertGroup(GroupFull externalGroup) {
        return Group.builder()
                .id(externalGroup.getId())
                .name(externalGroup.getName())
                .screenName(nonNull(externalGroup.getScreenName()))
                .status(nonNull(externalGroup.getStatus()))
                .description(nonNull(externalGroup.getDescription()))

                .type(nonNull(externalGroup.getType()))
                .verified(String.valueOf(externalGroup.isVerified()))
                .isClosed(nonNull(externalGroup.getIsClosed()))
                .ageLimits(nonNull(externalGroup.getAgeLimits()))
                .subscribersCount(nonNull(externalGroup.getMembersCount()))

                .city(nonNull(externalGroup.getCity()))
                .country(nonNull(externalGroup.getCountry()))

                .photoLink(externalGroup.getPhoto200())

                .links(listToString(nonNull(externalGroup.getLinks())))
                .contacts(listToString(nonNull(externalGroup.getContacts())))
                .site(nonNull(externalGroup.getSite()))

                .deactivated(nonNull(externalGroup.getDeactivated()))
                .build();
    }

    @Override
    public List<Group> convertGroups(List<GroupFull> externalGroups) {
        return externalGroups.stream()
                .map(this::convertGroup)
                .collect(Collectors.toList());
    }

    private String nonNull(String data) {
        return data != null ? data : "";
    }

    private <ExternalPojo> List<ExternalPojo> nonNull(List<ExternalPojo> data) {
        return data != null ? data : Lists.newArrayList();
    }

    private String nonNull(Object object) {
        return object != null ? object.toString() : "";
    }

    private String nonNull(Enum enumObject) {
        return enumObject != null ? enumObject.name() : "";
    }

    private <ExternalPojo> String listToString(List<ExternalPojo> data) {
        return String.join("; ", data.stream()
                .map(this::entityToString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private <ExternalPojo> String entityToString(ExternalPojo entity) {
        if (entity instanceof Career) {
            return careerToString((Career) entity);
        } else if (entity instanceof Military) {
            return militaryToString((Military) entity);
        } else if (entity instanceof University) {
            return universityToString((University) entity);
        } else if (entity instanceof School) {
            return schoolToString((School) entity);
        } else if (entity instanceof WallpostAttachment) {
            return postAttachmentToString((WallpostAttachment) entity);
        } else if (entity instanceof LinksItem) {
            return linkToString((LinksItem) entity);
        } else if (entity instanceof ContactsItem) {
            return contactToString((ContactsItem) entity);
        } else if (entity instanceof Geo) {
            return geoToString((Geo) entity);
        } else if (entity != null) {
            return entity.toString();
        } else {
            return "";
        }
    }

    private String careerToString(Career career) {
        String company = StringUtils.isNotEmpty(career.getCompany()) ? career.getCompany() : "";
        String position = StringUtils.isNotEmpty(career.getPosition()) ? ", " + career.getPosition() : "";

        Integer from = career.getFrom();
        Integer until = career.getUntil();
        String date = from != null
                ? "(" + from + " - " + (until != null ? until : "Till now") + ")"
                : "";

        return company + position + date;
    }

    private String militaryToString(Military military) {
        String name = StringUtils.isNotEmpty(military.getUnit()) ? military.getUnit() : "";

        Integer from = military.getFrom();
        Integer until = military.getUntil();
        String date = from != null
                ? "(" + from + " - " + (until != null ? until : "Till now") + ")"
                : "";

        return name + date;
    }

    private String universityToString(University university) {
        String name = StringUtils.isNotEmpty(university.getName()) ? university.getName() : "";
        String faculty = StringUtils.isNotEmpty(university.getFacultyName()) ? ", " + university.getFacultyName() : "";
        String chair = StringUtils.isNotEmpty(university.getChairName()) ? ", " + university.getChairName() : "";
        String educationStatus = StringUtils.isNotEmpty(university.getEducationStatus()) ? ", " + university.getEducationStatus() : "";
        String educationForm = StringUtils.isNotEmpty(university.getEducationForm()) ? ", " + university.getEducationForm() : "";
        String date = university.getGraduation() != null ? ", #" + university.getGraduation().toString() : "";

        return name + faculty + chair + educationStatus + educationForm + date;
    }

    private String schoolToString(School school) {
        if (school.getName() != null) {
            String name = StringUtils.isNotEmpty(school.getName()) ? school.getName() + ", " : "";
            String type = StringUtils.isNotEmpty(school.getTypeStr()) ? school.getTypeStr() : "";

            Integer from = school.getYearFrom();
            Integer to = school.getYearTo() != null
                    ? school.getYearTo()
                    : school.getYearGraduated();

            String date = from != null
                    ? "(" + from + " - " + (to != null ? to : "Till now") + ")"
                    : "";

            return type + name + date;
        }
        return null;
    }

    private String postAttachmentToString(WallpostAttachment postAttachment) {
        WallpostAttachmentType type = postAttachment.getType();
        String attachment = "";

        switch (type) {
            case APP:
                attachment = postAttachment.getApp().getName();
                break;
            case DOC:
                attachment = postAttachment.getDoc().getTitle();
                break;
            case LINK:
                attachment = postAttachment.getLink().getUrl();
                break;
            case NOTE:
                attachment = postAttachment.getNote().getTitle();
                break;
            case PAGE:
                attachment = postAttachment.getPage().getTitle();
                break;
            case POLL:
                attachment = postAttachment.getPoll().getQuestion()
                        + " [" + String.join("; ", postAttachment.getPoll().getAnswers().stream().map(Answer::getText).collect(Collectors.toList())) + "]";
                break;
            case ALBUM:
                attachment = postAttachment.getAlbum().getTitle();
                break;
            case AUDIO:
                attachment = postAttachment.getAudio().getArtist() + " - " + postAttachment.getAudio().getTitle();
                break;
            case PHOTO:
                attachment = photoToString(postAttachment.getPhoto());
                break;
            case VIDEO:
                attachment = postAttachment.getVideo().getTitle();
                break;
            case MARKET:
                attachment = postAttachment.getMarket().getTitle();
                break;
            case GRAFFITI:
                attachment = postAttachment.getGraffiti().getPhoto586();
                break;
            case PHOTOS_LIST:
                attachment = String.join(", ", nonNull(postAttachment.getPhotosList()));
                break;
            case POSTED_PHOTO:
                attachment = postAttachment.getPostedPhoto().getPhoto604();
                break;
            case MARKET_MARKET_ALBUM:
                attachment = postAttachment.getMarketMarketAlbum().getTitle();
                break;
        }

        return type.name() + ": " + attachment;
    }

    private String linkToString(LinksItem link) {
        return link.getName() != null && link.getUrl() != null ? link.getName() + " - " + link.getUrl() : null;
    }

    private String contactToString(ContactsItem contact) {
        Integer userId = contact.getUserId();
        String email = contact.getEmail() != null ? " - " + contact.getEmail() : "";
        String phone = contact.getPhone() != null ? " - " + contact.getPhone() : "";
        return userId != null ? userId + email + phone : null;
    }

    private String photoToString(Photo photo) {
        if (photo.getPhoto2560() != null) {
            return photo.getPhoto2560();
        }
        if (photo.getPhoto1280() != null) {
            return photo.getPhoto1280();
        }
        if (photo.getPhoto807() != null) {
            return photo.getPhoto807();
        }
        if (photo.getPhoto604() != null) {
            return photo.getPhoto604();
        }
        if (photo.getPhoto130() != null) {
            return photo.getPhoto130();
        }
        return nonNull(photo.getPhoto75());
    }

    private String geoToString(Geo geo) {
        String type = geo.getType() + ", ";
        String coordinates = geo.getCoordinates();

        Place place = geo.getPlace();
        return type + coordinates + (place != null ? "(" + place.getTitle() + ")" : "");
    }
}
