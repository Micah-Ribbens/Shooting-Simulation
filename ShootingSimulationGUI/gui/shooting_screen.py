from base.colors import *
from base.drawable_objects import Ellipse, GameObject
from base.equations import LineSegment, Point
from base.important_variables import screen_length, screen_height, distance_from_hub, y_points_from_center
from gui_components.sub_screen import SubScreen
from base.important_variables import scalar_divider, scalar, hub_height


class ShootingScreen(SubScreen):
    """A screen that displays the shooting arrows and robots"""

    hub = None

    def __init__(self, height_used_up, start_xy, new_xy):
        """Initializes the object"""

        self.scale_xy(start_xy)
        self.scale_xy(new_xy)

        hub_length = scalar * 2
        hub = Ellipse(scalar * 2 - hub_length / 2, height_used_up, hub_height, hub_length)
        hub.color = green

        start_arrow = LineSegment(Point(hub.x_midpoint, hub.bottom + distance_from_hub + y_points_from_center * scalar),
                                  Point(hub.x_midpoint, hub.y_midpoint))

        start_arrow.color = red
        arrow_to_hub = LineSegment(Point(start_xy[0], start_xy[1] + hub.bottom + distance_from_hub),
                                   Point(hub.x_midpoint, hub.y_coordinate))
        arrow_to_hub.color = purple
        corrected_arrow = LineSegment(Point(start_xy[0], start_xy[1] + hub.bottom + distance_from_hub),
                                      Point(new_xy[0], new_xy[1] + height_used_up))
        corrected_arrow.color = magenta

        robot_length = screen_length * .05
        robot_start = GameObject(start_arrow.start_point.x_coordinate, start_arrow.start_point.y_coordinate, robot_length, robot_length)
        robot_end = GameObject(arrow_to_hub.start_point.x_coordinate, arrow_to_hub.start_point.y_coordinate, robot_length, robot_length)
        robot_end.color = black
        robot_start.color = dark_green

        self.components = [hub, start_arrow, robot_start, arrow_to_hub, corrected_arrow, robot_end]
        self.hub = hub
        self.shift_all_components()

    def shift_all_components(self):
        """Shifts all the components a certain direction"""
        shift_right = (screen_length - scalar * scalar_divider) * .8

        for component in self.components:
            if type(component) == LineSegment:
                component.start_point.x_coordinate += shift_right
                component.end_point.x_coordinate += shift_right

            else:
                component.x_coordinate += shift_right

    def scale_xy(self, xy):
        """Scales the xy for the screen, so it looks correct"""

        xy[0] *= scalar
        xy[1] *= scalar






