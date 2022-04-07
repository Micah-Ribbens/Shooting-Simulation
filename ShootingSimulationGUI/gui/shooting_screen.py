from base.colors import *
from base.drawable_objects import Ellipse, GameObject
from base.equations import LineSegment, Point
from base.important_variables import screen_length, screen_height, distance_from_hub, y_points_from_center
from gui_components.sub_screen import SubScreen
from base.important_variables import *


class ShootingScreen(SubScreen):
    """A screen that displays the shooting arrows and robots"""

    hub = None

    def __init__(self, height_used_up, start_xy, new_xy):
        """Initializes the object"""

        start_xy = self.scale_xy(start_xy)
        new_xy = self.scale_xy(new_xy)
        scaled_hub_point = self.scale_xy(hub_point)
        scaled_robot_start_point = self.scale_xy(robot_start_point)
        hub_length = hub_radius * 2

        hub = Ellipse(scaled_hub_point[0] - hub_length / 2, scaled_hub_point[1], hub_radius, hub_length)
        hub.color = green

        start_arrow = LineSegment(Point(scaled_robot_start_point[0], scaled_robot_start_point[1]),
                                  Point(scaled_hub_point[0], scaled_hub_point[1]))

        start_arrow.color = red
        arrow_to_hub = LineSegment(Point(start_xy[0], start_xy[1]),
                                   Point(scaled_hub_point[0], scaled_hub_point[1]))
        arrow_to_hub.color = purple
        corrected_arrow = LineSegment(Point(start_xy[0], start_xy[1]),
                                      Point(new_xy[0], new_xy[1]))
        corrected_arrow.color = magenta

        robot_length = screen_length * .05
        robot_start = GameObject(start_arrow.start_point.x_coordinate, start_arrow.start_point.y_coordinate, robot_length, robot_length)
        robot_end = GameObject(arrow_to_hub.start_point.x_coordinate, arrow_to_hub.start_point.y_coordinate, robot_length, robot_length)
        robot_end.color = black
        robot_start.color = dark_green

        self.components = [hub, start_arrow, robot_start, arrow_to_hub, corrected_arrow, robot_end]
        self.hub = hub
        self.height_used_up = height_used_up
        self.shift_all_components()

    def shift_all_components(self):
        """Shifts all the components a certain direction"""
        shift_right = (screen_length - scalar * scalar_divider) * .8
        shift_down = self.height_used_up

        for component in self.components:
            if type(component) == LineSegment:
                component.start_point.x_coordinate += shift_right
                component.end_point.x_coordinate += shift_right
                component.start_point.y_coordinate += shift_down
                component.end_point.y_coordinate += shift_down

            else:
                component.x_coordinate += shift_right
                component.y_coordinate += shift_down

    def scale_xy(self, xy):
        """Scales the xy for the screen, so it looks correct"""
        scaled_xy = [xy[0], xy[1]]
        scaled_xy[0] *= scalar
        scaled_xy[1] *= scalar

        return scaled_xy
